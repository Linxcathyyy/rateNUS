<template>
  <v-footer absolute dark class="orange px-4 py-12">
    <v-container>
      <v-row id="footer-link-row">
        <div>GitHub</div>
        <div>About</div>
        <div>Team</div>
        <div>Contributors</div>
        <div>FAQ</div>
        <div>Contribute to RateNUS</div>
      </v-row>
      <v-row>
        <div class="ml-2 my-4">Data correct as at {{ dateTime }}.</div>
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
  },
  mounted() {
    this.dateTime = new Date();
  },
};
</script>

<style>
#footer-link-row > div {
  font-weight: bold;
  color: rgb(235, 226, 210);
  background: transparent;
  margin: 0px 8px;
}

#footer-link-row > div:hover {
  color: white;
  cursor: pointer;
}
</style>
