<template>
  <div class="login main">
    <AuthForm></AuthForm>
  </div>
  <div class="footer">
    <div class="">
      <div class="">© 2021 Акционерная фирма «САММЕР ДЕЙ БАНК». Генеральная лицензия Центрального Банка Гогвардса №5
        выдана 10.09.3010 г.
      </div>
    </div>
  </div>
</template>
<script>
import AuthForm from "@/components/AuthForm";
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import 'dotenv/config'

export default {
  name: 'LoginPage',
  components: {
    AuthForm
  },
  props: {},
  beforeMount() {
  }
}
</script>
<style>
.login.main {
  background-image: url('~@/assets/loginbg.jpg');
  background-origin: content-box;
  background-size: cover;
  min-height: 900px;
  min-width: 100%;
  display: flex;
}

.footer {
  box-shadow: inset 0 1px 0 0 #dddfe0;
  background-color: #e8e8ea;
  padding: 30px;
}
</style>