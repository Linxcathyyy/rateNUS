<template>
  <div v-if="item != null">
    <v-card flat class="mx-16 mb-12">
      <v-card-title>{{ item.name }}</v-card-title>
      <v-card-text>
        <v-row align="center" class="mx-0">
          <v-rating
            :value="item.rating"
            color="amber"
            dense
            half-increments
            readonly
            size="14"
          ></v-rating>

          <div class="grey--text ms-4">
            {{ getRating }} {{ getCommentCount }}
          </div>
        </v-row>

        <v-row align="center" class="mx-0">
          <v-icon class="icon">mdi-map-marker</v-icon>
          <div class="my-4 text-subtitle-1">
            {{ item.location }}
          </div>
        </v-row>
      </v-card-text>
    </v-card>

    <ImageSlideShow :imageUrls="item.imageUrl" />
    <v-card flat class="mx-16 my-12">
      <v-card-text>
        <v-row align="center" class="mx-0">
          <div class="mb-4 text-left">
            {{ item.description }}
          </div>
        </v-row>
      </v-card-text>
    </v-card>
    <v-divider class="mx-16"></v-divider>
    <Facilities :facilities="this.item.facilities" />
    <v-divider class="mx-16"></v-divider>
    <CommentForm />
    <v-divider class="mx-16"></v-divider>
    <Comments :type="type" />
  </div>
</template>

<script>
import HostelRequest from "../../httpRequests/HostelRequest";
import StallRequest from "../../httpRequests/StallRequest";
import Comments from "../util/Comments.vue";
import Facilities from "./Facilities.vue";
import CommentForm from "../util/CommentForm.vue";
import ImageSlideShow from "../util/ImageSlideShow.vue";

export default {
  name: "ItemViewMore",
  components: {
    Comments,
    CommentForm,
    Facilities,
    ImageSlideShow,
  },
  props: {
    type: String,
  },
  data() {
    return {
      item: null,
      commentList: [],
    };
  },
  methods: {
    getIndividualHostel() {
      HostelRequest.getIndividualHostel(this.$route.params.hostelId)
        .then((response) => {
          console.log(response.data);
          this.item = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getIndividualStall() {
      StallRequest.getIndividualStall(this.$route.params.stallId)
        .then((response) => {
          console.log(response.data);
          this.item = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
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

  mounted() {
    if (this.type === "hostel") {
      this.getIndividualHostel();
    } else if (this.type === "stall") {
      this.getIndividualStall();
    } else {
      // study area
    }
  },
};
</script>

<style scoped></style>
