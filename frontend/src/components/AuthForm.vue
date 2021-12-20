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
          />
        </div>
        <div class="">
          <va-input
              class="mb-4"
              v-model="password"
              label="Пароль"
          />
        </div>
        <div class="auth-button-wrapper">
          <va-button :rounded="false" class="" @click="auth">Войти</va-button>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import {VaButton, VaInput} from 'vuestic-ui'

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
      login: '', password: ''
    }
  },
  methods: {
    auth: function () {
      this.$http.get('/someUrl').then(response => {

        // get body data
        this.someData = response.body;

      }, response => {
        // error callback
      });
      console.log(this.login);
      console.log(this.password);
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