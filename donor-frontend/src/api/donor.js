import request from './request'

export function getDonorById(id) {
  return request.get(`/api/donors/${id}`)
}

export function updateDonor(id, data) {
  return request.put(`/api/donors/${id}`, data)
}
