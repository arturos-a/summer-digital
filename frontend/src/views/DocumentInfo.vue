<template>
  <Nav></Nav>
  <div class="document">
    <div class="doc-header">
      <h2>Документ</h2>
      <div class="doc-field-name">Номер</div>
      <div class="doc-field">{{ documentUuid }}</div>
      <div class="doc-field-name">Номер счет отправителя</div>
      <div class="doc-field">{{ documentData.documentFields.srcAccountNumber }}</div>
      <div class="doc-field-name">Статус</div>
      <div class="doc-field">{{ documentData.statusCode }}</div>
      <div class="doc-field-name">Дата документа</div>
      <div class="doc-field">{{ documentData.created }}</div>
    </div>
    <div class="doc-body"></div>
  </div>
</template>
<script>
import Nav from "@/components/Navigation";
import {getDpcumentUuid} from "@/constants/constants";
import apiClient from "@/client/clientApi";


export default {
  name: 'DocumentInfo',
  components: {Nav},
  props: {},
  data: function () {
    return {
      documentUuid: "",
      documentData: {
        documentFields: {srcAccountNumber: ""}
      }
    }
  },
  created() {
    {
      {
        if (this.$route && this.$route.params.documentUuid) {
          localStorage.setItem(getDpcumentUuid(), JSON.stringify(this.$route.params.documentUuid));
        }
        this.documentUuid = JSON.parse(localStorage.getItem(getDpcumentUuid()));
        let config = {
          params: {
            documentUuid: this.documentUuid
          },
        }
        apiClient.get('/api/document', config).then(response => {
          this.documentData = response.data;
          console.log(JSON.stringify(this.documentData));
          this.loadModal = false;
          this.showModal = true;
          if (response.data.statusCode === "OK") {
            this.modalText = "Документ успешно отправлен в банк";
          } else {
            this.modalText = response.data.statusText;
          }
        });
      }
    }
  }
}
</script>
<style>

</style>