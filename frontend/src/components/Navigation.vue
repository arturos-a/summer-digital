<template>
  <header class="nav-header">
    <div class="nav-grid">
      <div class="nav-logo">
        <div class="logo" title="Главная"></div>
      </div>
      <div class="nav-break wrapper"></div>
      <div class="menu-wrapper" v-for="route in routes" v-bind:key="route.id">
        <router-link :to="{ name: route.name}" class="nav-link" active-class="active">
          {{ route.title }}
        </router-link>
      </div>
      <div class="client-info">
        <i class="material-icons user-icon">person</i>
        <span>{{ clientName }}</span>
        <a @click="logout" class="nav-link logout-icon" active-class="active">
          <i class="material-icons">exit_to_app</i>
        </a>
      </div>
      <div>
      </div>
    </div>
  </header>
</template>
<script>

import {mdiAccount} from '@mdi/js'
import apiClient from "@/client/clientApi";
import {getTokenName} from "@/constants/constants";
import router from "@/router/router";


export default {
  name: "Nav",
  props: {},
  data: function () {
    return {
      routes: [
        {id: "1", title: "Продукты", name: 'main'}, {id: "2", title: "Оплата услуг", name: ''}, {
          id: "3",
          title: "История",
          name: ''
        },
      ],
      clientName: "", svgPath: mdiAccount
    }
  },
  methods: {
    logout: function () {
      apiClient.get('/logout').then(response => {
        localStorage.removeItem(getTokenName());
        router.push("/")
      }, response => {
        let responseStatus = JSON.parse(JSON.stringify(response));
        this.errorLoginMsg = responseStatus.status == 401 ? 'Клиент с указанными данными не найден' : 'Ошибка';
      }).catch(error => {
        this.loading = false;
      });
    }
  },
  mounted() {
    apiClient.get('/api/client-info').then(response => {
      this.clientName = response.data.firstName + " " + response.data.middleName;
    }, response => {
      let responseStatus = JSON.parse(JSON.stringify(response));
      this.errorLoginMsg = responseStatus.status == 401 ? 'Клиент с указанными данными не найден' : 'Ошибка авторизации';
    }).catch(error => {
      this.loading = false;
    });
  }
}</script>
<style>
.nav-header {
  align-items: stretch;
  display: flex;
  flex-direction: row;
  min-height: 64px;
  background: rgb(255, 255, 255);
  padding: 1.25rem 0;
  line-height: 2.5rem;
  position: relative;
  z-index: 25;
  border-bottom: 1px solid rgb(216, 223, 230);
}

.nav-grid {
  display: grid;
  grid-template-columns: repeat(20, 1fr);
  grid-column-gap: 1.25rem;
  min-height: 0;
  width: 100%;
}

.nav-logo {
  margin: 0;
  padding: 0;
  grid-column: span 3;
}

div.logo {
  color: transparent;
  border: 0;
  width: 12.5rem;
  height: 3.5rem;
  background-repeat: no-repeat;
  background-position: center center;
  background-image: url('~@/assets/logo.png');
  background-size: contain;
  display: block;
  margin: auto;
}

.nav-break.wrapper {
  grid-column: span 1;
}

.menu-wrapper, .nav-break {
  grid-column: span 2;
  line-height: 3.5rem;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  position: relative;
}

.nav-break::before {
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  width: 1px;
  height: 60px;
  background: rgb(216, 223, 230);
}

ul {
  font-size: 0;
  display: grid;
  grid-template-columns: repeat(12, 1fr);
  grid-column-gap: 1.25rem;
}

ul li {
  font-size: 14px;
  display: inline-block;
  width: 20%;
  margin: 0;
  padding: 0;
}

.client-info {
  grid-column: span 8;
  line-height: 3.5rem;
  text-align: right;
  padding-right: 10px;
}

.client-info .user-icon {
  vertical-align: sub;
}

.client-info .logout-icon {
  vertical-align: sub;
}


</style>