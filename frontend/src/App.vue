<template>
  <div id="app">
    <HostelList/>
  </div>
</template>

<script>
import HostelList from './components/HostelList.vue'

export default {
  name: 'App',
  components: {
    HostelList
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
</style>
