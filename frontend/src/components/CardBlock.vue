<template>
  <div class="card-full-info">
    <div class="card-header-block">
      <div class="card-img-block">
        <div class="card-title-block">{{ card.title }}</div>
        <div class="card-number-block">{{ card.cardNumber }}</div>
        <div class="card-owner-block">{{ card.cardOwner }}</div>
        <div class="card-exp-block">{{ card.expiredDate }}</div>
      </div>
      <div class="card-info">
        <div class="balance-block">
          <div class="balance-name">Баланс</div>
          <div class="balance-val">{{ card.amount }} {{ card.currency }}</div>
        </div>
        <div class="payment-block">
          <div class="transfer-button-wrapper">
            <va-button :to="{ name: 'transfer', params: { uuid: card.uuid }}" :rounded="false" :loading="loading"
                       class="">Пополнить
            </va-button>
          </div>
          <div class="pay-button-wrapper">
            <va-button :to="{ name: 'payment', params: { uuid: card.uuid }}" :rounded="false" :loading="loading"
                       class="">Оплатить
            </va-button>
          </div>
        </div>
      </div>
    </div>
    <div class="card-history">
      <h2>История</h2>
      <div class="history-body">
        <ul class="history-list">
          <li class="history-item" v-for="item in history" :key="item.uuid">
            <div class="operation-title"><span class="material-icons">{{ item }}</span>{{ item.documentTitle }}</div>
            <div class="operation-amount">{{ item.amount }}</div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
import apiClient from "@/client/clientApi";

export default {
  name: 'CardBlock',
  components: {},
  props: ['uuid'],
  data: function () {
    return {
      card: {},
      history: {}
    }
  },
  created() {
    let config = {
      params: {
        uuid: this.uuid
      },
    }
    let historyConfig = {
      params: {
        cardUuid: this.uuid
      },
    }
    apiClient.get('/api/card', config).then(response => {
      this.card = response.data;
    }, response => {
      let responseStatus = JSON.parse(JSON.stringify(response));
      this.errorLoginMsg = responseStatus.status == 401 ? 'Клиент с указанными данными не найден' : 'Ошибка';
    }).catch(error => {
      this.loading = false;
    });
    apiClient.get('/api/history', historyConfig).then(response => {
      this.history = response.data;
    }, response => {
      let responseStatus = JSON.parse(JSON.stringify(response));
      this.errorLoginMsg = responseStatus.status == 401 ? 'Клиент с указанными данными не найден' : 'Ошибка';
    }).catch(error => {
      this.loading = false;
    });
  },
  methods: {
    payment: function () {
      apiClient.post('/api/payment', {
        'uuid': this.card.uuid
      })
    },
    transfer: function () {
      apiClient.post('/api/transfer', {
        'uuid': this.card.uuid
      })
    }
  }
}
</script>
<style>
.card-img-block {
  background-image: url('~@/assets/card_bg.png');
  background-size: contain;
  height: 300px;
  background-repeat: no-repeat;
  width: 500px;
  font-family: sans-serif;
  margin-left: 200px;
  float: left;
}

.card-title-block {
  position: absolute;
  top: 164px;
  left: 435px;
}

.card-number-block {
  position: absolute;
  top: 246px;
  left: 436px;
}

.card-owner-block {
  position: absolute;
  top: 337px;
  left: 256px;
  width: 269px;
}

.card-exp-block {
  position: absolute;
  top: 339px;
  left: 572px;
}

.card-info {
  width: 1000px;
  height: 262px;
  float: left;
  margin-top: 24px;
  background: linear-gradient(to right, white, #f150642b);
}

.balance-name {
  font-size: 12px;
  width: 140px;
  text-align: left;
}

.balance-val {
  font-size: 36px;
  margin-top: 15px;
  margin-left: 15px;
  text-align: left;
}

.transfer-button-wrapper {
  float: left;
  width: 200px;
}

.pay-button-wrapper {
  float: left;
  width: 200px;
}

.payment-block {
  width: 400px;
  height: 100px;
  margin-top: 100px;
}

.card-header-block {
  width: 100%;
  height: 322px;
}

.card-history {
  width: 100%;
}

div.card-history .history-body {
  width: 1024px;
  margin: auto;
}

ul.history-list {
  display: block;
}

ul.history-list li.history-item {
  display: block;
  width: 100%;
  line-height: 50px;
  height: 50px;
}

ul.history-list li.history-item .operation-title {
  float: left;
}

ul.history-list li.history-item .operation-amount {
  float: right;
}
</style>