import Login from '@/views/LoginPage.vue'
import NotFound from '@/views/NotFound.vue'
import {createRouter, createWebHistory} from 'vue-router'
import Main from "@/components/Main";

const routes = [{
    path: '/login',
    name: 'login',
    component: Login,
}, {
    path: '/main',
    name: 'main',
    component: Main,
    meta: {
        requiresAuth: true
    }
}, {
    path: "/:catchAll(.*)",
    component: NotFound,
}]
const router = createRouter({
    history: createWebHistory(),
    routes
})
router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (localStorage.getItem('token') == null) {
            next({
                path: '/login',
                params: {nextUrl: to.fullPath}
            })
        } else {
            next()
        }
    } else {
        next()
    }
})

export default router