import {createApp} from 'vue'
import App from './App.vue'
import router from './router/router.js'
import { VuesticPlugin } from 'vuestic-ui'
import 'vuestic-ui/dist/vuestic-ui.css'


createApp(App).use(router).use(VuesticPlugin).mount('#app')
