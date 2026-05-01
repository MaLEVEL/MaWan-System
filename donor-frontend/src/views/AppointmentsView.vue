<template>
  <div class="appointments">
    <div class="page-header">
      <h2>{{ $t('appointment.title') }}</h2>
      <el-button type="primary" @click="showDialog = true">
        <el-icon><Plus /></el-icon> {{ $t('appointment.newAppointment') }}
      </el-button>
    </div>

    <el-tabs v-model="activeTab" @tab-change="loadAppointments">
      <el-tab-pane :label="$t('appointment.upcoming')" name="upcoming" />
      <el-tab-pane :label="$t('appointment.all')" name="all" />
    </el-tabs>

    <div v-loading="loading" class="appointment-list">
      <el-empty v-if="appointments.length === 0 && !loading" :description="$t('appointment.noAppointments')" />
      <el-card v-for="apt in appointments" :key="apt.id" class="appointment-card" shadow="hover">
        <div class="card-header">
          <el-tag :type="statusTagType(apt.status)">{{ $t('appointmentStatus.' + apt.status) }}</el-tag>
          <span class="apt-type">{{ $t('appointmentType.' + apt.type) }}</span>
        </div>
        <div class="card-body">
          <p><el-icon><Calendar /></el-icon> {{ formatDateTime(apt.startTime) }}</p>
          <p><el-icon><Location /></el-icon> {{ apt.location || $t('appointment.pending') }}</p>
        </div>
        <div class="card-footer" v-if="apt.status === 'PLANNED' || apt.status === 'CONFIRMED'">
          <el-button type="danger" size="small" text @click="cancelAppointment(apt)">{{ $t('appointment.cancelAppointment') }}</el-button>
        </div>
      </el-card>
    </div>

    <el-dialog v-model="showDialog" :title="$t('appointment.newAppointment')" width="480px">
      <el-form ref="bookingFormRef" :model="bookingForm" :rules="bookingRules" label-width="90px">
        <el-form-item :label="$t('appointment.appointmentType')" prop="appointmentType">
          <el-select v-model="bookingForm.appointmentType" style="width: 100%">
            <el-option :label="$t('appointmentType.BLOOD')" value="BLOOD" />
            <el-option :label="$t('appointmentType.BONE_MARROW')" value="BONE_MARROW" />
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('appointment.appointmentTime')" prop="startTime">
          <el-date-picker v-model="bookingForm.startTime" type="datetime" :placeholder="$t('appointment.selectTime')"
            style="width: 100%" value-format="YYYY-MM-DDTHH:mm:ss"
            :disabled-date="d => d < new Date()" />
        </el-form-item>
        <el-form-item :label="$t('appointment.location')">
          <el-input v-model="bookingForm.location" :placeholder="$t('appointment.locationPlaceholder')" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showDialog = false">{{ $t('common.cancel') }}</el-button>
        <el-button type="primary" :loading="submitting" @click="createAppointment">{{ $t('appointment.confirmAppointment') }}</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { useAuthStore } from '@/stores/auth'
import { getAppointments, createAppointment as createApi, updateAppointment } from '@/api/appointment'
import { formatDateTime } from '@/utils/format'
import { ElMessage, ElMessageBox } from 'element-plus'

const { t } = useI18n({ useScope: 'global' })
const authStore = useAuthStore()
const loading = ref(false)
const submitting = ref(false)
const appointments = ref([])
const activeTab = ref('upcoming')
const showDialog = ref(false)
const bookingFormRef = ref()

const bookingForm = reactive({
  appointmentType: 'BLOOD',
  startTime: '',
  location: ''
})

const bookingRules = computed(() => ({
  appointmentType: [{ required: true, message: t('appointment.selectType'), trigger: 'change' }],
  startTime: [{ required: true, message: t('appointment.selectTime'), trigger: 'change' }]
}))

function statusTagType(status) {
  const map = { PLANNED: 'primary', CONFIRMED: 'success', CANCELLED: 'info', NO_SHOW: 'warning' }
  return map[status] || 'info'
}

async function loadAppointments() {
  loading.value = true
  try {
    const params = { donorId: authStore.donorId, size: 50, sort: 'startTime,desc' }
    const res = await getAppointments(params)
    const list = res.content || res || []
    if (activeTab.value === 'upcoming') {
      appointments.value = list.filter(a => a.status === 'PLANNED' || a.status === 'CONFIRMED')
    } else {
      appointments.value = list
    }
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }
}

async function createAppointment() {
  await bookingFormRef.value.validate()
  submitting.value = true
  try {
    const startTime = bookingForm.startTime
    const endDate = new Date(new Date(startTime).getTime() + 3600000)
    const endTime = endDate.toISOString().slice(0, 19)
    await createApi({
      donorId: authStore.donorId,
      type: bookingForm.appointmentType,
      startTime,
      endTime,
      location: bookingForm.location || t('appointment.pending')
    })
    ElMessage.success(t('appointment.createSuccess'))
    showDialog.value = false
    bookingForm.appointmentType = 'BLOOD'
    bookingForm.startTime = ''
    bookingForm.location = ''
    loadAppointments()
  } catch (e) {
    console.error(e)
  } finally {
    submitting.value = false
  }
}

async function cancelAppointment(apt) {
  try {
    await ElMessageBox.confirm(t('appointment.cancelConfirm'), t('appointment.cancelTitle'), { type: 'warning' })
    await updateAppointment(apt.id, { ...apt, status: 'CANCELLED' })
    ElMessage.success(t('appointment.cancelled'))
    loadAppointments()
  } catch (e) {
    if (e !== 'cancel') console.error(e)
  }
}

onMounted(loadAppointments)
</script>

<style scoped>
.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.page-header h2 {
  margin: 0;
}

.appointment-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-top: 16px;
}

.appointment-card .card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
}

.apt-type {
  font-size: 14px;
  font-weight: 600;
  color: #606266;
}

.card-body p {
  margin: 4px 0;
  font-size: 14px;
  color: #606266;
  display: flex;
  align-items: center;
  gap: 6px;
}

.card-footer {
  margin-top: 8px;
  text-align: right;
}
</style>
