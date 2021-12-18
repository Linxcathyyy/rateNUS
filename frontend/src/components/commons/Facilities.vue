<template>
  <v-card flat class="mb-5">
    <v-card-title>Facilities</v-card-title>
    <v-card-text>
      <v-row align="center" class="mx-0">
        <div
          v-for="facility in this.facilities"
          :key="facility"
          class="facility"
        >
          <div>{{ facility }}</div>
        </div>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: "Facilities",
  props: ["facilities"],
  methods: {
    camelCaseToNormalCase(text) {
      const result = text.replace(/([A-Z])/g, " $1");
      const finalResult = result.charAt(0).toUpperCase() + result.slice(1);
      return finalResult;
    },
  },

  beforeMount() {
    for (let i = 0; i < this.facilities.length; i++) {
      this.facilities[i] = this.camelCaseToNormalCase(this.facilities[i]);
    }
  },
};
</script>

<style>
.facility {
  display: inline-block;
  margin: 10px 20px 0px 0px;
  padding: 2px 5px;
  border: 1px grey solid;
  border-radius: 1rem;
}

.facility:hover {
  background: #FF6D00;
  color: white;
  border-color: #FF6D00;
  cursor: default;
}
</style>
