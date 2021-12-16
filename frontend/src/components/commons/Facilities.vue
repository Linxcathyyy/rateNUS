<template>
      <v-card flat class="mx-16 mb-12">
        <v-card-title>Facilities</v-card-title>
        <v-card-text>
          <v-row
            align="center"
            class="mx-0"
          >
            <div v-for="facility in this.facilities" :key="facility" class="facility">
                <p> {{ facility }} </p>
            </div>
          </v-row>
        </v-card-text>
      </v-card>
</template>

<script>

export default ({
    name: "Facilities",
    props: ["facilities"],
    methods: {
        camelCaseToNormalCase(text) {
            const result = text.replace(/([A-Z])/g, " $1");
            const finalResult = result.charAt(0).toUpperCase() + result.slice(1);
            return finalResult;
        }
    },

    beforeMount() {
        for (let i = 0; i < this.facilities.length; i++) {
                    this.facilities[i] = this.camelCaseToNormalCase(this.facilities[i]);
                }
    },
}
)
</script>

<style>

.facilities {
max-width: 100%;
  background: rgba(214, 238, 245, 0.5);
  text-align: left;
  border-radius: 30px;
  padding: 0.5rem 2rem;
margin: 20px 0px;
}
.facility {
  display: inline-block;
  padding: 0px 20px 0px 0px;
}
</style>