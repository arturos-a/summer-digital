import Login from '@/views/LoginPage.vue'
import NotFound from '@/views/NotFound.vue'
import {createRouter, createWebHistory} from 'vue-router'
import Main from "@/components/Main";
import {getTokenName} from "@/constants/constants";
import Card from "@/views/Card";
import Transfer from "@/views/Transfer";
import DocumentInfo from "@/views/DocumentInfo";

const routes = [{
    path: '/client/login',
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
    path: '/client/card',
    name: 'card',
    component: Card,
    meta: {
        requiresAuth: true
    }
}, {
    path: '/client/transfer',
    name: 'transfer',
    component: Transfer,
    meta: {
        requiresAuth: true
    }
}, {
    path: '/client/document',
    name: 'documentInfo',
    component: DocumentInfo,
    meta: {
        requiresAuth: true
    }
}, {
    path: '/client/payment',
    name: 'payment',
    component: Transfer,
    meta: {
        requiresAuth: true
    }
}, {
    path: "/:catchAll(.*)",
    component: NotFound,
}

]
const router = createRouter({
    history: createWebHistory(),
    routes
})
router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (localStorage.getItem(getTokenName()) == null) {
            next({
                path: '/client/login',
                params: {nextUrl: to.fullPath}
            })
        } else {
            next()
        }
    } else {
        if (to.fullPath == '/client/login') {
            next()
        } else {
            next({
                path: '/client/login',
                params: {nextUrl: to.fullPath}
            })
        }
    }
})

export default router