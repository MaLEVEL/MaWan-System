<template>
  <div class="home">
    <h2>{{ $t('home.welcome') }}</h2>
    <el-row :gutter="16" class="stat-cards">
      <el-col :xs="12" :sm="8">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-icon" style="background: #e8f4fd"><el-icon :size="28" color="#409eff"><Calendar /></el-icon></div>
          <div class="stat-info">
            <div class="stat-value">{{ stats.upcomingAppointments }}</div>
            <div class="stat-label">{{ $t('home.upcomingAppointments') }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="8">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-icon" style="background: #f0f9eb"><el-icon :size="28" color="#67c23a"><List /></el-icon></div>
          <div class="stat-info">
            <div class="stat-value">{{ stats.totalDonations }}</div>
            <div class="stat-label">{{ $t('home.totalDonations') }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="8">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-icon" style="background: #fdf6ec"><el-icon :size="28" color="#e6a23c"><Document /></el-icon></div>
          <div class="stat-info">
            <div class="stat-value">{{ stats.lastCheckResult || $t('home.none') }}</div>
            <div class="stat-label">{{ $t('home.lastCheckResult') }}</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="16" style="margin-top: 20px">
      <el-col :xs="24" :sm="12">
        <el-card>
          <template #header>{{ $t('home.quickActions') }}</template>
          <div class="quick-actions">
            <el-button type="primary" @click="$router.push('/appointments')">
              <el-icon><Calendar /></el-icon> {{ $t('home.bookAppointment') }}
            </el-button>
            <el-button @click="$router.push('/donations')">
              <el-icon><List /></el-icon> {{ $t('home.viewRecords') }}
            </el-button>
            <el-button @click="$router.push('/medical-checks')">
              <el-icon><Document /></el-icon> {{ $t('home.checkResults') }}
            </el-button>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="12">
        <el-card>
          <template #header>{{ $t('home.personalSummary') }}</template>
          <div v-if="donor" class="profile-summary">
            <p><strong>{{ $t('home.name') }}：</strong>{{ donor.firstName }} {{ donor.lastName }}</p>
            <p><strong>{{ $t('home.bloodType') }}：</strong>{{ $t('bloodType.' + donor.bloodType) }}</p>
            <p><strong>{{ $t('home.passportNumber') }}：</strong>{{ donor.passportNumber }}</p>
            <p><strong>{{ $t('home.activeStatus') }}：</strong><el-tag :type="donor.active ? 'success' : 'danger'">{{ donor.active ? $t('home.active') : $t('home.inactive') }}</el-tag></p>
          </div>
          <el-empty v-else :description="$t('common.loading')" />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { useAuthStore } from '@/stores/auth'
import { getDonorById } from '@/api/donor'
import { getAppointments } from '@/api/appointment'
import { getDonations } from '@/api/donation'
import { getMedicalChecks } from '@/api/medicalCheck'

const { t } = useI18n({ useScope: 'global' })
const authStore = useAuthStore()
const donor = ref(null)

const stats = ref({
  upcomingAppointments: 0,
  totalDonations: 0,
  lastCheckResult: ''
})

onMounted(async () => {
  const did = authStore.donorId
  if (!did) return

  try {
    donor.value = await getDonorById(did)

    const [appointments, donations, checks] = await Promise.all([
      getAppointments({ donorId: did, size: 100 }).catch(() => ({ content: [] })),
      getDonations({ donorId: did, size: 1 }).catch(() => ({ totalElements: 0 })),
      getMedicalChecks({ donorId: did, size: 1, sort: 'checkDate,desc' }).catch(() => ({ content: [] }))
    ])

    const apptList = appointments.content || appointments || []
    stats.value.upcomingAppointments = apptList.filter(a =>
      a.status === 'PLANNED' || a.status === 'CONFIRMED'
    ).length

    stats.value.totalDonations = donations.totalElements || (Array.isArray(donations) ? donations.length : 0)

    const checkList = checks.content || checks || []
    if (checkList.length > 0) {
      stats.value.lastCheckResult = t('conclusion.' + checkList[0].conclusion) || checkList[0].conclusion
    }
  } catch (e) {
    console.error('Failed to load dashboard data', e)
  }
})
</script>

<style scoped>
.stat-cards {
  margin-top: 16px;
}

.stat-card {
  display: flex;
  align-items: center;
  gap: 16px;
}

.stat-card :deep(.el-card__body) {
  display: flex;
  align-items: center;
  gap: 16px;
}

.stat-icon {
  width: 52px;
  height: 52px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.stat-value {
  font-size: 24px;
  font-weight: 700;
  color: #303133;
}

.stat-label {
  font-size: 13px;
  color: #909399;
}

.quick-actions {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.profile-summary p {
  margin: 8px 0;
  font-size: 14px;
  color: #606266;
}
</style>
