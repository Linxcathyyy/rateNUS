<template>
  <v-card
    :loading="loading"
    class="mx-auto my-12"
    max-width="1000"
  >
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-img
      height="400"
      :src="hostel.imageUrl[0]"
    ></v-img>

    <v-card-title>{{ hostel.name }}</v-card-title>

    <v-card-text>
      <v-row
        align="center"
        class="mx-0"
      >
        <v-rating
          :value="hostel.rating"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>

        <div class="grey--text ms-4">
          {{ hostel.rating }} ({{ hostel.commentCount }})
        </div>
      </v-row>

        <v-row align="center" class="mx-0">
        <div class="my-4 text-subtitle-1">
            {{ hostel.location }}
        </div>
        </v-row>
        <v-row align="center" class="mx-0">
            <div class="mb-4 text-left">
                {{ hostel.description}}
            </div>
        </v-row>
    </v-card-text>
    
  </v-card>
</template>

<script>
  export default {
    props: {
        hostel: Object,
    },
    data: () => ({
      loading: false,
      selection: 1,
    }),
    methods: {
      reserve () {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },
    },
  }
</script>