<template>
  <v-footer absolute dark class="grey lighten-3 px-4 py-12">
    <v-container>
      <v-row id="footer-link-row">
        <div @click="redirect()">GitHub</div>
        <div>About</div>
        <div>Team</div>
        <div>Contributors</div>
        <div>FAQ</div>
        <div>Contribute to RateNUS</div>
      </v-row>
      <v-row>
        <div class="ml-2 my-4 data">Data correct as at {{ dateTime }}.</div>
      </v-row>
    </v-container>
  </v-footer>
</template>

<script>
export default {
  data() {
    return {
      dateTime: "",
    };
  },
  methods: {
    // add @click methods here
    redirect() {
      window.open("https://github.com/Linxcathyyy/rateNUS");
    }
  },
  mounted() {
    this.dateTime = new Date();
  },
};
</script>

<style>
#footer-link-row > div {
  font-weight: bold;
  /* color: rgb(235, 226, 210); */
  color: #616161;
  background: transparent;
  margin: 0px 8px;
}

.data {
    color: #616161;
}

#footer-link-row > div:hover {
  color: #ff6d00;
  cursor: pointer;
}
</style>
