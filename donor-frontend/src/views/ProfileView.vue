<template>
  <div class="profile">
    <h2>{{ $t('profile.title') }}</h2>
    <el-card v-if="donor" style="margin-top: 16px">
      <el-form ref="formRef" :model="form" label-width="100px" :disabled="!editing">
        <el-divider content-position="left">{{ $t('profile.basicInfo') }}</el-divider>
        <el-row :gutter="16">
          <el-col :xs="24" :sm="12">
            <el-form-item :label="$t('profile.firstName')">
              <el-input :model-value="donor.firstName" disabled />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12">
            <el-form-item :label="$t('profile.lastName')">
              <el-input :model-value="donor.lastName" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="16">
          <el-col :xs="24" :sm="12">
            <el-form-item :label="$t('profile.dateOfBirth')">
              <el-input :model-value="formatDate(donor.dateOfBirth)" disabled />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12">
            <el-form-item :label="$t('profile.gender')">
              <el-input :model-value="$t('gender.' + donor.gender)" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="16">
          <el-col :xs="24" :sm="12">
            <el-form-item :label="$t('profile.passportNumber')">
              <el-input :model-value="donor.passportNumber" disabled />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12">
            <el-form-item :label="$t('profile.bloodType')">
              <el-input :model-value="$t('bloodType.' + donor.bloodType)" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item :label="$t('profile.hlaType')" v-if="donor.hlaType">
          <el-input :model-value="donor.hlaType" disabled />
        </el-form-item>

        <el-divider content-position="left">{{ $t('profile.contactInfo') }}</el-divider>
        <el-form-item :label="$t('profile.phone')" prop="phone">
          <el-input v-model="form.phone" :placeholder="$t('profile.phone')" />
        </el-form-item>
        <el-form-item :label="$t('profile.email')" prop="email">
          <el-input v-model="form.email" :placeholder="$t('profile.email')" />
        </el-form-item>
        <el-form-item :label="$t('profile.address')">
          <el-input v-model="form.address" :placeholder="$t('profile.address')" />
        </el-form-item>
      </el-form>

      <div class="actions">
        <el-button v-if="!editing" type="primary" @click="editing = true">{{ $t('common.edit') }}</el-button>
        <template v-else>
          <el-button @click="cancelEdit">{{ $t('common.cancel') }}</el-button>
          <el-button type="primary" :loading="saving" @click="saveProfile">{{ $t('common.save') }}</el-button>
        </template>
      </div>
    </el-card>
    <el-empty v-else :description="$t('common.loading')" />
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { useAuthStore } from '@/stores/auth'
import { getDonorById, updateDonor } from '@/api/donor'
import { formatDate } from '@/utils/format'
import { ElMessage } from 'element-plus'

const { t } = useI18n({ useScope: 'global' })
const authStore = useAuthStore()
const donor = ref(null)
const editing = ref(false)
const saving = ref(false)
const formRef = ref()

const form = reactive({
  phone: '',
  email: '',
  address: ''
})

onMounted(async () => {
  if (!authStore.donorId) return
  try {
    donor.value = await getDonorById(authStore.donorId)
    form.phone = donor.value.phone || ''
    form.email = donor.value.email || ''
    form.address = donor.value.address || ''
  } catch (e) {
    console.error(e)
  }
})

function cancelEdit() {
  editing.value = false
  form.phone = donor.value.phone || ''
  form.email = donor.value.email || ''
  form.address = donor.value.address || ''
}

async function saveProfile() {
  saving.value = true
  try {
    const data = {
      firstName: donor.value.firstName,
      lastName: donor.value.lastName,
      dateOfBirth: donor.value.dateOfBirth,
      gender: donor.value.gender,
      passportNumber: donor.value.passportNumber,
      bloodType: donor.value.bloodType,
      hlaType: donor.value.hlaType,
      phone: form.phone,
      email: form.email,
      address: form.address,
      active: donor.value.active
    }
    donor.value = await updateDonor(authStore.donorId, data)
    editing.value = false
    ElMessage.success(t('profile.saveSuccess'))
  } catch (e) {
    console.error(e)
  } finally {
    saving.value = false
  }
}
</script>

<style scoped>
.actions {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>
