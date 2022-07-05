<template>
  <div class="product-block">
    <div class="cards-block">
      <div class="item-block-title">
        <h3 class="block-name">
          <div class="title-wrapper">Карты</div>
          <i class="item-icon bank-card-icon"></i>
        </h3>
      </div>
      <div class="product-block-content">
        <ul class="product-item bank-cards">
          <li v-for="item in cards" :key="item.uuid">
            <div class="card-wrapper">
              <va-hover v-model="hover[item.uuid]">
                <va-card :to="{ name: 'card', params: { uuid: item.uuid }}" :outlined="!hover[item.uuid]">
                  <va-card-title>{{ item.title }}</va-card-title>
                  <va-card-content>{{ item.amount }} {{ item.currency }}
                    {{ item.cardNumber }} exp. {{ item.expiredDate }}
                  </va-card-content>
                </va-card>
              </va-hover>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div class="accounts-block">
      <div class="item-block-title">
        <h3 class="block-name">
          <div class="title-wrapper">Счета</div>
          <i class="item-icon accounts-icon"></i>
        </h3>
      </div>
    </div>
    <div class="credits-block">
      <div class="item-block-title">
        <h3 class="block-name">
          <div class="title-wrapper">Кредиты</div>
          <i class="item-icon credit-icon"></i>
        </h3>
      </div>
    </div>
  </div>
</template>
<script>

import apiClient from "@/client/clientApi";

export default {
  name: "Products",
  data: function () {
    return {
      cards: {},
      accounts: {},
      deposits: {},
      credits: {},
      hover: []
    }
  },
  components: {},
  props: {},
  created() {
    apiClient.get('api/products').then(response => {
      this.cards = response.data.cards;
      this.accounts = response.data.cards;
      this.deposits = response.data.cards;
      this.credits = response.data.cards;
      this.hover.push(this.cards.map(({uuid}) => ({uuid: uuid, hover: false})));
      this.hover.push(this.accounts.map(({uuid}) => ({uuid: uuid, hover: false})));
      this.hover.push(this.deposits.map(({uuid}) => ({uuid: uuid, hover: false})));
      this.hover.push(this.credits.map(({uuid}) => ({uuid: uuid, hover: false})));
      this.hover = this.hover.reduce(function (map, obj) {
        map[obj.uuid] = obj.hover;
        return map;
      }, {});
    }, response => {
      this.loading = false;
      let responseStatus = JSON.parse(JSON.stringify(response));
      this.errorLogin = true;
      this.errorLoginMsg = responseStatus.status == 401 ? 'Клиент с указанными данными не найден' : 'Ошибка авторизации';
    }).catch(error => {
      this.loading = false;
    });
  }
}</script>
<style>
.product-block {
  margin-top: 10px;
  min-height: 64px;
}

.product-block .product-item {
  grid-template-columns: 300px 300px 300px 300px 300px 300px;
}

ul.product-item.bank-cards li {
  width: 200px;
}

ul.product-item.bank-cards li .va-card {
  width: 185px;
  margin: auto;
}

h3.block-name {
  font-size: 16px;
  text-align: left;
  margin-left: 10px;
  height: 35px;
  vertical-align: middle;
  background-color: #f5f5f5;
}

h3.block-name .item-icon {
  float: left;
  height: 32px;
  width: 32px;
}

h3.block-name .item-icon.bank-card-icon {
  background-position: 188px 189px;
  background-image: url('~@/assets/_icons.png');
}

h3.block-name .item-icon.accounts-icon {
  background-position: 152px 189px;
  background-image: url('~@/assets/_icons.png');
}

h3.block-name .item-icon.credit-icon {
  background-position: 263px 78px;
  background-image: url('~@/assets/_icons.png');
}

.product-block div.item-block-title {
  height: 64px;
}

.block-name .title-wrapper {
  float: left;
  margin-right: 10px;
  margin-top: 6px;
}

.product-block .product-block-content {
  height: 150px;
}
</style>
