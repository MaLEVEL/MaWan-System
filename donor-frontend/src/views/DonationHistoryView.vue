<template>
  <div class="donations">
    <h2>{{ $t('donation.title') }}</h2>

    <div class="filters" style="margin-top: 16px">
      <el-select v-model="filters.type" :placeholder="$t('donation.donationType')" clearable style="width: 140px" @change="loadData">
        <el-option v-for="(label, key) in donationTypeLabels" :key="key" :label="label" :value="key" />
      </el-select>
      <el-select v-model="filters.status" :placeholder="$t('common.status')" clearable style="width: 120px; margin-left: 10px" @change="loadData">
        <el-option v-for="(label, key) in donationStatusLabels" :key="key" :label="label" :value="key" />
      </el-select>
    </div>

    <div v-loading="loading" class="donation-list" style="margin-top: 16px">
      <el-empty v-if="donations.length === 0 && !loading" :description="$t('donation.noDonations')" />
      <el-card v-for="d in donations" :key="d.id" class="donation-card" shadow="hover">
        <div class="card-row">
          <span class="label">{{ $t('donation.type') }}：</span>
          <span>{{ $t('donationType.' + d.type) }}</span>
          <el-tag :type="statusTagType(d.status)" style="margin-left: auto">
            {{ $t('donationStatus.' + d.status) }}
          </el-tag>
        </div>
        <div class="card-row">
          <span class="label">{{ $t('donation.date') }}：</span>
          <span>{{ formatDateTime(d.plannedAt) }}</span>
        </div>
        <div class="card-row" v-if="d.volumeMl">
          <span class="label">{{ $t('donation.volume') }}：</span>
          <span>{{ d.volumeMl }} {{ $t('donation.unit') }}</span>
        </div>
        <div class="card-row" v-if="d.notes">
          <span class="label">{{ $t('common.notes') }}：</span>
          <span>{{ d.notes }}</span>
        </div>
      </el-card>
    </div>

    <el-pagination v-if="total > pageSize" :current-page="page" :page-size="pageSize" :total="total"
      layout="prev, pager, next" @current-change="handlePageChange" style="margin-top: 16px; text-align: center" />
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { useAuthStore } from '@/stores/auth'
import { getDonations } from '@/api/donation'
import { formatDateTime } from '@/utils/format'

const { t } = useI18n({ useScope: 'global' })
const authStore = useAuthStore()
const loading = ref(false)
const donations = ref([])
const page = ref(1)
const pageSize = 10
const total = ref(0)

const filters = reactive({ type: '', status: '' })

const donationTypeLabels = computed(() => ({
  BLOOD: t('donationType.BLOOD'),
  BONE_MARROW: t('donationType.BONE_MARROW')
}))

const donationStatusLabels = computed(() => ({
  PLANNED: t('donationStatus.PLANNED'),
  COMPLETED: t('donationStatus.COMPLETED'),
  CANCELLED: t('donationStatus.CANCELLED'),
  REJECTED: t('donationStatus.REJECTED')
}))

function statusTagType(status) {
  const map = { PLANNED: 'primary', COMPLETED: 'success', CANCELLED: 'info', REJECTED: 'danger' }
  return map[status] || 'info'
}

async function loadData() {
  loading.value = true
  try {
    const params = {
      donorId: authStore.donorId,
      page: page.value - 1,
      size: pageSize,
      sort: 'plannedAt,desc'
    }
    if (filters.type) params.type = filters.type
    if (filters.status) params.status = filters.status

    const res = await getDonations(params)
    donations.value = res.content || res || []
    total.value = res.totalElements || donations.value.length
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }
}

function handlePageChange(p) {
  page.value = p
  loadData()
}

onMounted(loadData)
</script>

<style scoped>
.donation-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.donation-card .card-row {
  display: flex;
  align-items: center;
  margin: 6px 0;
  font-size: 14px;
  color: #606266;
}

.donation-card .label {
  color: #909399;
  width: 60px;
  flex-shrink: 0;
}
</style>
