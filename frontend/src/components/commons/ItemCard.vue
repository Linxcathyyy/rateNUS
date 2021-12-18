<template>
  <v-card :loading="loading" class="mx-auto my-12" max-width="1000">
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-img height="400" :src="item.imageUrl[0]"></v-img>

    <v-card-title style="word-break: break-word">{{ item.name }}</v-card-title>

    <v-card-text>
      <v-row align="center" class="mx-0">
        <v-rating
          :value="item.rating"
          color="amber"
          background-color="grey"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>

        <div class="grey--text ms-4">{{ getRating }} {{ getCommentCount }}</div>
      </v-row>

      <v-row align="center" class="mt-5">
        <div
          v-if="type == 'stall'"
          class="black--text font-weight-bold ms-4 text-subtitle-1"
        >
          ${{ item.lowestPrice }} - ${{ item.highestPrice }}
        </div>
      </v-row>

      <v-row align="center" class="mx-0">
        <v-icon class="icon">mdi-map-marker</v-icon>
        <div class="my-4 text-subtitle-1">
          {{ item.location }}
        </div>
      </v-row>
      <v-row align="center" class="mx-0">
        <div class="mb-4 text-left">
          {{ item.description }}
        </div>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  props: {
    type: String,
    item: Object,
  },
  data: () => ({
    loading: false,
    selection: 1,
  }),
  methods: {
    reserve() {
      this.loading = true;
      setTimeout(() => (this.loading = false), 2000);
    },
  },
  computed: {
    getRating() {
      if (this.item.rating === -1) {
        return "No rating";
      } else {
        return this.item.rating.toPrecision(2);
      }
    },
    getCommentCount() {
      if (this.item.commentCount <= 1) {
        return `(${this.item.commentCount} review)`;
      } else {
        return `(${this.item.commentCount} reviews)`;
      }
    },
  },
};
</script>