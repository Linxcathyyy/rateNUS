<template>
  <div v-if="item != null">
    <v-card flat class="my-4">
      <v-card-title style="word-break: break-word">{{
        item.name
      }}</v-card-title>
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
    <v-container>
      <v-carousel delimiter-icon="mdi-minus" hide-delimiter-background cycle show-arrows-on-hover :height="imageHeight">
        <v-carousel-item
          v-for="imgURL in item.imageUrl"
          :key="imgURL"
          :src="imgURL"
        >
        </v-carousel-item>
      </v-carousel>
    </v-container>

    <v-card flat class="my-4">
      <v-card-text>
        <v-row align="center" class="mx-0">
          <div class="mb-4 text-left">
            {{ item.description }}
          </div>
        </v-row>
      </v-card-text>
    </v-card>
    <v-divider></v-divider>
    <div v-if="type === 'hostel'">
      <Facilities :facilities="this.item.facilities" />
      <v-divider></v-divider>
    </div>
    <CommentForm :type="type" />
    <v-divider></v-divider>
    <Comments :type="type" />
  </div>
</template>

<script>
import HostelRequest from "../../httpRequests/HostelRequest";
import StallRequest from "../../httpRequests/StallRequest";
import StudyAreaRequest from "../../httpRequests/StudyAreaRequest";
import Comments from "../util/Comments.vue";
import Facilities from "./Facilities.vue";
import CommentForm from "../util/CommentForm.vue";

export default {
  name: "ItemViewMore",
  components: {
    Comments,
    CommentForm,
    Facilities,
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
      HostelRequest.getIndividualHostel(this.$route.params.id)
        .then((response) => {
          console.log(response.data);
          this.item = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getIndividualStall() {
      StallRequest.getIndividualStall(this.$route.params.id)
        .then((response) => {
          console.log(response.data);
          this.item = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getIndividualStudyArea() {
      StudyAreaRequest.getIndividualStudyArea(this.$route.params.id)
        .then((response) => {
          console.log(response.data);
          this.item = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    scrollToTop() {
      window.scrollTo(0, 0);
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
    imageHeight () {
        switch (this.$vuetify.breakpoint.name) {
          case 'xs': return 200
          case 'sm': return 300
          case 'md': return 350
          case 'lg': return 400
          case 'xl': return 500
          default: return 500
        }
      },
  },

  mounted() {
    if (this.type === "hostel") {
      this.getIndividualHostel();
    } else if (this.type === "stall") {
      this.getIndividualStall();
    } else if (this.type === "studyArea") {
      this.getIndividualStudyArea();
    }
    this.scrollToTop();
  },
};
</script>

<style scoped></style>
