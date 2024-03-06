<template>
  <div class="row">
    <button @click="getAllGemeenten">Alle gemeenten</button>
    <button @click="getGemeentenGesorteerdOpInwoners">Sorteer op inwoners</button>
    <button @click="getGemeentenGesorteerdAlfabetisch">Sorteer op alfabet</button>
  </div>
  <table id="data-display" v-if="data">
    <thead>
    <tr>
      <th>ID</th>
      <th>Gemeente</th>
      <th>Inwoners</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="item in data" :key="item.id">
      <td>{{item.id}}</td>
      <td>{{ item.gemeente }}</td>
      <td>{{ item.inwoners }}</td>
    </tr>
    </tbody>
  </table>
</template>

<script>
import axios from 'axios';
import { baseUrl } from "@/apiConfig";

export default {
  name: 'LandingPage',
  data() {
    return {
      data: null,
    };
  },
  methods: {
    async getAllGemeenten() {
      const response = await axios.get(`${baseUrl}/gemeenten`);
      this.data = response.data;
    },
    async getGemeentenGesorteerdOpInwoners() {
      const response = await axios.get(`${baseUrl}/gemeenten/sorteer/inwoners`);
      this.data = response.data;
    },
    async getGemeentenGesorteerdAlfabetisch() {
      const response = await axios.get(`${baseUrl}/gemeenten/sorteer/alfabetisch`);
      this.data = response.data;
    },
  },
};
</script>


<style scoped>
.row {
  display: flex;
  justify-content: center;
  margin-top: 5%;
}

#data-display {
  margin-top: 3%;
  width: 100%;
  border-collapse: collapse;
}

#data-display th, #data-display td {
  border: 1px solid transparent;
  padding: 8px;
  text-align: center;
}

button {
  border-radius: 10px;
  border: transparent;
  background-color: transparent;
  color: #1F3A7F;
  font-size: 1rem;
  font-weight: bold;
  padding: 10px 40px;
  letter-spacing: 1px;
  text-transform: uppercase;
  cursor: pointer;
}
</style>
