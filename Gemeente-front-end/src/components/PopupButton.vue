<template>
  <div class="popup">
    <div class="popup-inner">
      <slot />
      <div v-if="data" class="spacing-bottom">
        <p>Id: {{data.id}}</p>
        <p>Gemeente: {{ data.gemeente }}</p>
        <p>Inwoners: {{ data.inwoners }}</p>
      </div>
      <div class="spacing-bottom">
        <input class="inputText" type="text" placeholder="Gemeente" v-model="gemeente" @change="getSpecifiekeGemeente"/>
      </div>
      <button class="popup-close" @click="TogglePopup">Sluit informatie</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { baseUrl } from "@/apiConfig";

export default {
  name: 'PopupButton',
  props: ['TogglePopup'],
  data() {
    return {
      gemeente: '',
      data: null,
    }
  },
  methods: {
    eersteHoofdletter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    async getSpecifiekeGemeente(){
      const gemeente = this.eersteHoofdletter(this.gemeente);
      try {
        const response = await axios.get(`${baseUrl}/gemeenten/naam/${gemeente}`);
        this.data = response.data;
      } catch (error) {
        if (error.response && error.response.status === 403) {
          alert('Gemeente niet gevonden');
        } else {
          console.error("Er is een error opgetreden "+ error)
          alert("Helaas is er een fout opgetreden.")
        }
      }
    }
  }
}
</script>

<style lang="scss" scoped>
button {
  border-radius: 20px;
  border: 1px solid #1F3A7F;
  background-color: #1F3A7F;
  margin-top: 5px;
  color:  #DD9621;
  font-size: 1rem;
  font-weight: bold;
  padding: 10px 40px;
  letter-spacing: 1px;
  text-transform: uppercase;
  cursor: pointer;
  transition: transform .1s ease-in;

  &:active {
    transform: scale(.9);
  }

  &:focus {
    outline: none;
  }
}

button.invert {
  background-color: transparent;
  border-color: #fff;
}

.spacing-bottom{
  margin-bottom: 10px;
}

.spacing-right{
  margin-right: 60px;
}

.inputText{
  border-radius: 20px;
  border: 1px solid #1F3A7F;
  width: 65%;
  padding: 20px 40px;

}

.popup {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	z-index: 99;
	background-color: rgba(0, 0, 0, 0.5);
	display: flex;
	align-items: center;
	justify-content: center;

	.popup-inner {
    background: rgba(255, 255, 255, 0.65);
    border: none;
    color: rgb(255, 255, 255);
    font-size: 20px;
    border-radius: 12px;
    padding: 20px 35px 20px;
    margin-top: 50px;
    margin-bottom: 100px;
    display: inline-block;
	}
}
</style>
