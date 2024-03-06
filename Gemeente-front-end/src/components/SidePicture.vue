<template>
  <div class="container">
    <img alt="Vue logo" src="../assets/img.png">
  </div>
  <button @click="() => TogglePopup('buttonTrigger')"> Specifieke informatie </button>
  <PopupButton
    v-if="popupTriggers.buttonTrigger"
    :TogglePopup="() => TogglePopup('buttonTrigger')">
    <h2>Specifieke informatie Gemeente</h2>
  </PopupButton>
</template>

<script>
import {ref} from "vue";
import PopupButton from "@/components/PopupButton.vue";

export default {
  components: {PopupButton},
  setup() {
    const popupTriggers = ref({
      buttonTrigger: false,
    });
    const TogglePopup = (trigger) => {
      popupTriggers.value[trigger] = !popupTriggers.value[trigger]
    }
    return {
      PopupButton,
      popupTriggers,
      TogglePopup
    }
  },
  name: 'SidePicture'
}
</script>
<style scoped>

h2{
  color:  #DD9621;
}
.container {
  width: 20%;
}

button {
  margin-top: 15%;
  display: flex;
  border-radius: 10px;
  border: transparent;
  background-color: transparent;
  color: #DD9621;
  font-size: 1rem;
  font-weight: bold;
  padding: 10px 40px;
  letter-spacing: 1px;
  text-transform: uppercase;
  cursor: pointer;
}

</style>
