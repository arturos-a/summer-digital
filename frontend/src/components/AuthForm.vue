<template>
  <div class="auth-block">
    <header class="logo-header">
      <dev data-unit="icon"
           class="logo-image"></dev>
    </header>
    <div class="auth-from-block">
      <form autocomplete="off" class="" id="homeAuth">
        <div class="">
          <va-input
              class="mb-4"
              v-model="login"
              label="Имя пользователя"
              :error-messages="errorLoginMsg"
              :error="errorLogin"
          />
        </div>
        <div class="">
          <va-input
              type="password"
              class="mb-4"
              v-model="password"
              label="Пароль"
              :error-messages="errorPasswordMsg"
              :error="errorPassword"
          />
        </div>
        <div class="auth-button-wrapper">
          <va-button :rounded="false" :loading="loading" class="" @click="auth">Войти</va-button>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import {VaButton, VaInput,} from 'vuestic-ui'
import apiClient from '@/client/clientApi.js'
import {sha256} from "js-sha256";
import router from "@/router/router";

export default {
  name: 'AuthForm',
  props: {
    msg: String,
    components: {
      VaButton,
      VaInput
    }
  },
  data() {
    return {
      login: '',
      password: '',
      loading: false,
      errorLogin: false,
      errorPassword: false,
      errorLoginMsg: '',
      errorPasswordMsg: ''
    }
  },
  methods: {
    auth: function () {
      this.errorLogin = false;
      this.errorPassword = false;
      this.errorLoginMsg = '';
      this.errorPasswordMsg = '';
      this.loading = true;
      let salt = sha256((Math.random() + 1).toString(36).substring(7) + Math.random());
      let password = sha256(salt + sha256(this.password));
      apiClient.post('auth', {
        'login': this.login,
        'salt': salt,
        'password': password
      }).then(response => {
        let token = response.data;
        localStorage.setItem("X-AUTH-TOKEN", token);
        this.loading = false;
        router.push({ name: 'main' });
      }, response => {
        this.loading = false;
        let responseStatus = JSON.parse(JSON.stringify(response));
        this.errorLogin = true;
        this.errorLoginMsg = responseStatus.status == 401 ? 'Клиент с указанными данными не найден' : 'Ошибка авторизации';
      }).catch(error => {

        this.loading = false;
      });
    }
  }
}
</script>
<style>
.auth-block {
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -ms-flex-direction: column;
  flex-direction: column;
  pointer-events: auto;
  position: relative;
  z-index: 4;
  min-width: 400px;
  padding: 20px 20px;
  margin-left: 60%;
  margin-top: 40px;
  background-color: #fff;
  flex-direction: column;
  pointer-events: auto;
  position: relative;
  z-index: 4;
  border-radius: 8px;
  height: 280px;
}

.auth-block .logo-header {
  height: 70px;
  width: 100%;
  display: flex;
}

.logo-image {
  background-image: url('~@/assets/logo.png');
  background-size: cover;
  min-height: 70px;
  min-width: 42%;
  margin: auto;
}

.auth-from-block {
  padding: 10px 0;
}

</style>