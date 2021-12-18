<template>
  <div class="auth-block">
    <div class="">
      <form autocomplete="off" class="" id="homeAuth">
        <div class="">
          <div class=""><label for="authByLogin"
                               class="">Логин</label>
          </div>
          <div><input data-unit="input:textbox" autocomplete="off" id="authByLogin" name="login" required=""
                      class="" value=""></div>
        </div>
        <div class="">
          <div class=""><label for="password"
                               class="">Пароль</label>
          </div>
          <div>
            <div class="">
              <div class=""><input data-unit="input:textbox" autocomplete="off" id="password"><!-- инпут --></div>
              <div class="">
                <!-- кнопка-->
              </div>
            </div>
          </div>
          <div class="">
            <div>
              <!-- кнопка войти-->
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
export default {
  name: 'AuthForm',
  props: {
    msg: String
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
  width: 100%;
  z-index: 4;
}
</style>