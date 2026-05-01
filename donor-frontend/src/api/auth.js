import request from './request'

export function login(data) {
  return request.post('/api/auth/login', data)
}

export function donorRegister(data) {
  return request.post('/api/auth/donor-register', data)
}

export function getCurrentUser() {
  return request.get('/api/users/me')
}
