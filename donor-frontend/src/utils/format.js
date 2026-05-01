import dayjs from 'dayjs'

export const bloodTypeLabels = {
  I_POSITIVE: 'I+ (A+)',
  I_NEGATIVE: 'I- (A-)',
  II_POSITIVE: 'II+ (B+)',
  II_NEGATIVE: 'II- (B-)',
  III_POSITIVE: 'III+ (AB+)',
  III_NEGATIVE: 'III- (AB-)',
  IV_POSITIVE: 'IV+ (O+)',
  IV_NEGATIVE: 'IV- (O-)'
}

export const genderLabels = {
  MALE: '男',
  FEMALE: '女',
  OTHER: '其他'
}

export const appointmentStatusLabels = {
  PLANNED: '已计划',
  CONFIRMED: '已确认',
  CANCELLED: '已取消',
  NO_SHOW: '未到'
}

export const appointmentTypeLabels = {
  BLOOD: '献血',
  BONE_MARROW: '骨髓捐献'
}

export const donationStatusLabels = {
  PLANNED: '已计划',
  COMPLETED: '已完成',
  CANCELLED: '已取消',
  REJECTED: '已拒绝'
}

export const donationTypeLabels = {
  WHOLE_BLOOD: '全血',
  PLASMA: '血浆',
  PLATELETS: '血小板',
  BONE_MARROW: '骨髓'
}

export const conclusionLabels = {
  FIT: '合格',
  UNFIT: '不合格',
  NEEDS_RECHECK: '需复查'
}

export const conclusionTagType = {
  FIT: 'success',
  UNFIT: 'danger',
  NEEDS_RECHECK: 'warning'
}

export function formatDate(date) {
  return date ? dayjs(date).format('YYYY-MM-DD') : ''
}

export function formatDateTime(date) {
  return date ? dayjs(date).format('YYYY-MM-DD HH:mm') : ''
}
