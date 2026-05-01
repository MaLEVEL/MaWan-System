import request from './request'

export function getMedicalChecks(params) {
  return request.get('/api/medical-checks', { params })
}
