import { createRouter, createWebHistory } from 'vue-router'
import { getToken } from '@/utils/auth'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/LoginView.vue'),
    meta: { public: true }
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/RegisterView.vue'),
    meta: { public: true }
  },
  {
    path: '/',
    component: () => import('@/views/LayoutView.vue'),
    redirect: '/home',
    children: [
      { path: 'home', name: 'Home', component: () => import('@/views/HomeView.vue') },
      { path: 'profile', name: 'Profile', component: () => import('@/views/ProfileView.vue') },
      { path: 'appointments', name: 'Appointments', component: () => import('@/views/AppointmentsView.vue') },
      { path: 'donations', name: 'Donations', component: () => import('@/views/DonationHistoryView.vue') },
      { path: 'medical-checks', name: 'MedicalChecks', component: () => import('@/views/MedicalChecksView.vue') }
    ]
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/home'
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = getToken()
  if (!to.meta.public && !token) {
    next({ path: '/login', query: { redirect: to.fullPath } })
  } else if (to.meta.public && token && (to.name === 'Login' || to.name === 'Register')) {
    next('/home')
  } else {
    next()
  }
})

export default router
