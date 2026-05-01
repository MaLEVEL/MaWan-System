const TOKEN_KEY = 'donor_token'
const USERNAME_KEY = 'donor_username'
const ROLES_KEY = 'donor_roles'
const DONOR_ID_KEY = 'donor_id'

export function getToken() {
  return localStorage.getItem(TOKEN_KEY)
}

export function setToken(token) {
  localStorage.setItem(TOKEN_KEY, token)
}

export function removeToken() {
  localStorage.removeItem(TOKEN_KEY)
}

export function getUsername() {
  return localStorage.getItem(USERNAME_KEY) || ''
}

export function setUsername(username) {
  localStorage.setItem(USERNAME_KEY, username)
}

export function getRoles() {
  const roles = localStorage.getItem(ROLES_KEY)
  return roles ? JSON.parse(roles) : []
}

export function setRoles(roles) {
  localStorage.setItem(ROLES_KEY, JSON.stringify(roles))
}

export function getDonorId() {
  const id = localStorage.getItem(DONOR_ID_KEY)
  return id ? Number(id) : null
}

export function setDonorId(id) {
  if (id) localStorage.setItem(DONOR_ID_KEY, String(id))
}

export function clearAuth() {
  localStorage.removeItem(TOKEN_KEY)
  localStorage.removeItem(USERNAME_KEY)
  localStorage.removeItem(ROLES_KEY)
  localStorage.removeItem(DONOR_ID_KEY)
}
