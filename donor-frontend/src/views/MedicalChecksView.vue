<template>
  <div class="medical-checks">
    <h2>{{ $t('medicalCheck.title') }}</h2>

    <div v-loading="loading" class="check-list" style="margin-top: 16px">
      <el-empty v-if="checks.length === 0 && !loading" :description="$t('medicalCheck.noChecks')" />
      <el-card v-for="c in checks" :key="c.id" class="check-card" shadow="hover">
        <div class="card-header">
          <span class="check-date">{{ formatDateTime(c.checkDate) }}</span>
          <el-tag :type="conclusionTagType[c.conclusion] || 'info'">
            {{ $t('conclusion.' + c.conclusion) }}
          </el-tag>
        </div>
        <div class="card-body">
          <el-row :gutter="16">
            <el-col :xs="12" :sm="8" v-if="c.hemoglobin">
              <div class="metric">
                <span class="metric-label">{{ $t('medicalCheck.hemoglobin') }}</span>
                <span class="metric-value">{{ c.hemoglobin }} g/dL</span>
              </div>
            </el-col>
            <el-col :xs="12" :sm="8" v-if="c.systolicPressure">
              <div class="metric">
                <span class="metric-label">{{ $t('medicalCheck.bloodPressure') }}</span>
                <span class="metric-value">{{ c.systolicPressure }}/{{ c.diastolicPressure }}</span>
              </div>
            </el-col>
          </el-row>
          <p v-if="c.notes" class="notes"><strong>{{ $t('common.notes') }}：</strong>{{ c.notes }}</p>
        </div>
      </el-card>
    </div>

    <el-pagination v-if="total > pageSize" :current-page="page" :page-size="pageSize" :total="total"
      layout="prev, pager, next" @current-change="handlePageChange" style="margin-top: 16px; text-align: center" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useAuthStore } from '@/stores/auth'
import { getMedicalChecks } from '@/api/medicalCheck'
import { conclusionTagType, formatDateTime } from '@/utils/format'

const authStore = useAuthStore()
const loading = ref(false)
const checks = ref([])
const page = ref(1)
const pageSize = 10
const total = ref(0)

async function loadData() {
  loading.value = true
  try {
    const res = await getMedicalChecks({
      donorId: authStore.donorId,
      page: page.value - 1,
      size: pageSize,
      sort: 'checkDate,desc'
    })
    checks.value = res.content || res || []
    total.value = res.totalElements || checks.value.length
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
.check-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.check-card .card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.check-date {
  font-size: 16px;
  font-weight: 600;
  color: #303133;
}

.metric {
  margin: 6px 0;
}

.metric-label {
  display: block;
  font-size: 12px;
  color: #909399;
}

.metric-value {
  font-size: 15px;
  font-weight: 600;
  color: #303133;
}

.notes {
  margin-top: 8px;
  font-size: 14px;
  color: #606266;
}
</style>
