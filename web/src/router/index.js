import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const routes = [
    {
        path: '/',
        redirect: '/dashboard',
        meta: { requiresAuth: true }
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/Auth/Login.vue'),
        meta: { guestOnly: true }
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('@/views/Auth/Register.vue'),
        meta: { guestOnly: true }
    },
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: () => import('@/views/Dashboard.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/players',
        name: 'Players',
        component: () => import('@/views/Players/PlayersList.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/players/add',
        name: 'AddPlayer',
        component: () => import('@/views/Players/PlayerForm.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/players/:id/edit',
        name: 'EditPlayer',
        component: () => import('@/views/Players/PlayerForm.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/players/:id',
        name: 'PlayerDetail',
        component: () => import('@/views/Players/PlayerDetail.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/clubs',
        name: 'Clubs',
        component: () => import('@/views/Clubs/ClubsList.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/clubs/add',
        name: 'AddClub',
        component: () => import('@/views/Clubs/ClubForm.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/clubs/:id/edit',
        name: 'EditClub',
        component: () => import('@/views/Clubs/ClubForm.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/clubs/:id',
        name: 'ClubDetail',
        component: () => import('@/views/Clubs/ClubDetail.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/transfers',
        name: 'Transfers',
        component: () => import('@/views/Transfers/TransfersList.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/transfers/submit',
        name: 'SubmitTransfer',
        component: () => import('@/views/Transfers/SubmitTransfer.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/transfers/review',
        name: 'ReviewTransfer',
        component: () => import('@/views/Transfers/ReviewTransfer.vue'),
        meta: { requiresAuth: true, requiresAdmin: true }
    },
    {
        path: '/matches',
        name: 'Matches',
        component: () => import('@/views/Matches/MatchesList.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/matches/add',
        name: 'AddMatch',
        component: () => import('@/views/Matches/MatchForm.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/matches/:id/edit',
        name: 'EditMatch',
        component: () => import('@/views/Matches/MatchForm.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/matches/:id',
        name: 'MatchDetail',
        component: () => import('@/views/Matches/MatchDetail.vue'),
        meta: { requiresAuth: true }
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

router.beforeEach((to, from, next) => {
    const authStore = useAuthStore()

    if (to.meta.requiresAuth && !authStore.isAuthenticated) {
        next('/login')
    } else if (to.meta.guestOnly && authStore.isAuthenticated) {
        next('/dashboard')
    } else if (to.meta.requiresAdmin && !authStore.user?.isAdmin) {
        next('/dashboard')
    } else {
        next()
    }
})

export default router