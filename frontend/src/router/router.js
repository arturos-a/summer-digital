import Login from '@/views/LoginPage.vue'
import NotFound from '@/views/NotFound.vue'
import {createRouter, createWebHistory} from 'vue-router'
import Main from "@/components/Main";
import {getTokenName} from "@/constants/constants";

const routes = [{
    path: '/',
    name: 'login',
    component: Login,
}, {
    path: '/client/main',
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
        if (localStorage.getItem(getTokenName()) == null) {
            next({
                path: '/',
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