<template>
  <div class="app">
    <div class="side">
      <side-picture/>
    </div>
    <div class="top">
      <LandingPage/>
    </div>
  </div>
</template>

<script>
import LandingPage from './components/LandingPage.vue'
import SidePicture from "@/components/SidePicture.vue";

export default {
  name: 'App',
  components: {
    SidePicture,
    LandingPage
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.app {
  display: flex;
  justify-content: space-between;
}

.side{
  margin-top: 5%;
  width: 35%;
}
.top{
  width: 65%;
}

</style>
