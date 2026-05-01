import request from './request'

export function getDonations(params) {
  return request.get('/api/donations', { params })
}
