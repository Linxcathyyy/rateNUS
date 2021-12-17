<template>
  <div v-if="hostel != null">
    <v-card flat class="mb-12">
      <v-card-title>{{ hostel.name }}</v-card-title>
      <v-card-text>
        <v-row align="center" class="mx-0">
          <v-rating
            :value="hostel.rating"
            color="amber"
            dense
            half-increments
            readonly
            size="14"
          ></v-rating>

          <div class="grey--text ms-4">
            {{ hostel.rating.toPrecision(2) }} ({{ hostel.commentCount }}
            reviews)
          </div>
        </v-row>

        <v-row align="center" class="mx-0">
          <v-icon class="icon">mdi-map-marker</v-icon>
          <div class="my-4 text-subtitle-1">
            {{ hostel.location }}
          </div>
        </v-row>
      </v-card-text>
    </v-card>
    <ImageSlideShow :imageUrls="hostel.imageUrl" />
    <v-card flat class="my-12">
      <v-card-text>
        <v-row align="center" class="mx-0">
          <div class="mb-4 text-left">
            {{ hostel.description }}
          </div>
        </v-row>
      </v-card-text>
    </v-card>
    <v-divider></v-divider>
    <Facilities :facilities="this.hostel.facilities" />
    <!-- The divider below is covered and had to add margin to show it. -->
    <v-divider id="unseen-divider"></v-divider>
    <CommentForm />
    <v-divider></v-divider>
    <Comments />
  </div>
</template>

<script>
import HostelRequest from "../../httpRequests/HostelRequest";
import Comments from "../Comments.vue";
import Facilities from "./Facilities.vue";
import CommentForm from "../CommentForm.vue";
import ImageSlideShow from "../ImageSlideShow.vue";

export default {
  name: "HostelViewMore",
  components: {
    Comments,
    CommentForm,
    Facilities,
    ImageSlideShow,
  },
  data() {
    return {
      hostel: null,
      commentList: [],
    };
  },
  methods: {
    getIndividualHostel() {
      HostelRequest.getIndividualHostel(this.$route.params.hostelId)
        .then((response) => {
          console.log(response.data);
          this.hostel = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },

  mounted() {
    this.getIndividualHostel();
  },
};
</script>

<style scoped></style>
