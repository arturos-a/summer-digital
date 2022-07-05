<template>
  <div class="transfer-block">
    <div class="form-title card-from-title">Откуда</div>
    <div class="card-from">
      <va-select v-model="valueFrom" :options="optionsFrom" :value-by="(option) => option.uuid" :error="isErrorFrom"
                 :error-messages="errorFromMsg"/>
    </div>
    <div class="form-title card-from-title">Куда</div>
    <div class="card-to">
      <va-select v-model="valueTo" :options="optionsTo" :value-by="(option) => option.uuid" :error="isErrorFrom"
                 :error-messages="errorFromMsg"/>
    </div>
    <div class="form-title sum-title">Сумма</div>
    <div class="summ-block">
      <va-input
          class="mb-4"
          v-model="transferSum" mask="numeral" :error="isErrorSum" :error-messages="errorSumMsg"
      />
    </div>
    <div class="button-block">
      <va-button :rounded="false" class="" @click="executeTransfer">Перевести</va-button>
    </div>
    <va-modal v-model="loadModal" hide-default-actions>
      <template #header>
        <h2>Отправка документа в банк</h2>
      </template>
      <slot>
        <div class="flex lg6 xs12 py-4 loading-circle">
          <va-progress-circle indeterminate without-transitions/>
        </div>
      </slot>
      <template #footer>
      </template>
    </va-modal>
    <va-modal v-model="showModal" hide-default-actions>
      <template #header>
        <h2>Результат перевода</h2>
      </template>
      <slot>
        <div>{{ modalText }}</div>
      </slot>
      <template #footer>
        <va-button :to="{ name: 'documentInfo', params: { documentUuid: this.documentUuid }}">
          Ok
        </va-button>
      </template>
    </va-modal>
  </div>
</template>
<script>
import apiClient from "@/client/clientApi";

export default {
  name: 'TransferBlock',
  components: {},
  props: ['uuid'],
  data: function () {
    return {
      toUuid: "",
      valueFrom: "",
      isErrorFrom: false,
      errorFromMsg: "",
      optionsFrom: [{"uuid": "1", "text": 'test'}],
      valueTo: "",
      optionsTo: [{"uuid": "1", "text": 'test'}],
      isErrorTo: false,
      errorToMsg: "",
      transferSum: 0,
      isErrorSum: false,
      errorSumMsg: "",
      showModal: false,
      modalText: "",
      loadModal: false,
      documentUuid: ""
    }
  },
  methods: {
    executeTransfer() {
      this.clear();
      if (this.valueFrom === this.valueTo) {
        this.isErrorFrom = true;
        this.errorFromMsg = "Недопустимо указывать одинаковые продукты в поле Откуда и Куда";
      }
      if (this.transferSum < 1) {
        this.isErrorSum = true;
        this.errorSumMsg = "Необходимо указывать сумму больше 0"
        return;
      }
      let optionsMap = this.optionsFrom.reduce(function (map, obj) {
        map[obj.uuid] = obj;
        return map;
      }, {});
      let amount = optionsMap[this.valueFrom].amount;
      if (this.transferSum > amount) {
        this.isErrorSum = true;
        this.errorSumMsg = "Указана сумма перевода превышающая остаток"
      }
      this.loadModal = true;
      apiClient.post('/api/performTransfer', {
        fromUuid: this.valueFrom,
        toUuid: this.valueTo,
        amount: this.transferSum
      }).then(response => {
        let document = response.data;
        this.documentUuid = document.docUuid;
        this.loadModal = false;
        this.showModal = true;
        if (response.data.statusCode === "OK") {
          this.modalText = "Документ успешно отправлен в банк";
        } else {
          this.modalText = response.data.statusText;
        }
      });
    },
    clear() {
      this.isErrorFrom = false;
      this.errorFromMsg = "";
      this.isErrorTo = false;
      this.errorToMsg = "";
      this.isErrorSumm = false;
      this.errorSummMsg = "";
      this.loadModal = false;
    }
  },

  created() {
    this.valueTo = this.uuid
    apiClient.get('/api/cardList').then(response => {
      this.optionsFrom = response.data;
      this.optionsTo = response.data;
    });
  }
}
</script>
<style>
.transfer-block {
  width: 900px;
  margin: auto;
}

.transfer-block .card-from {
  margin-top: 40px;
  margin-bottom: 40px;
}

.transfer-block .card-to {
  margin-top: 40px;
  margin-bottom: 40px;
}

.transfer-block .summ-block {
  margin-top: 40px;
  margin-bottom: 40px;
}

.form-title {
  text-align: left;
  margin-left: 20px;
}

.loading-circle {
  MARGIN: AUTO;
  WIDTH: 40PX;
}

</style>