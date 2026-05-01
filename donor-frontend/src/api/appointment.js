import request from './request'

export function createAppointment(data) {
  return request.post('/api/appointments', data)
}

export function getAppointments(params) {
  return request.get('/api/appointments', { params })
}

export function updateAppointment(id, data) {
  return request.put(`/api/appointments/${id}`, data)
}
