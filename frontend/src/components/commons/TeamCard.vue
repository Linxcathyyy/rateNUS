<template>
  <v-container>
    <v-card
      v-show="!this.isPortrait"
      rounded="xl"
      class="py-8 ml-8"
      width="80%"
    >
      <v-row class="align-center">
        <v-col class="mx-2" cols="3" id="avatar-col">
          <v-avatar size="90">
            <img
              :src="info.avatar_url"
              :alt="info.login"
              @click="redirectToUserPage()"
              :title="info.login"
            />
          </v-avatar>
        </v-col>
        <v-col class="mx-2" id="info-col">
          <v-card-title class="font-weight-bold"
            >{{ info.login }}
          </v-card-title>
          <v-card-subtitle class="text-left orange--text text--darken-3">
            Contributions: {{ info.contributions }}
          </v-card-subtitle>
          <v-card-text class="text-left text-body-1"
            >{{ description }}
          </v-card-text>
        </v-col>
      </v-row>
    </v-card>
    <v-card
      v-show="this.isPortrait"
      rounded="xl"
      class="py-8 my-4 ml-8"
      width="80%"
    >
      <v-avatar size="90">
        <img
          :src="info.avatar_url"
          :alt="info.login"
          @click="redirectToUserPage()"
          :title="info.login"
        />
      </v-avatar>
      <v-card-title class="font-weight-bold justify-center"
        >{{ info.login }}
      </v-card-title>
      <v-card-subtitle class="orange--text text--darken-3">
        Contributions: {{ info.contributions }}
      </v-card-subtitle>
      <v-card-text class="text-body-1">{{ description }} </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
export default {
  props: ["description", "info"],
  data() {
    return {
      screenWidth: 0,
      isPortrait: false,
    };
  },
  methods: {
    redirectToUserPage() {
      window.open(this.info.html_url);
    },
    handleScreenResize() {
      this.screenWidth = window.innerWidth;
      var widthToHeightRatio = window.innerWidth / window.innerHeight;
      if (widthToHeightRatio < 1) {
        this.isPortrait = true;
      } else {
        this.isPortrait = false;
      }
    },
  },
  created() {
    window.addEventListener("resize", this.handleScreenResize);
  },
  destroyed() {
    window.removeEventListener("resize", this.handleScreenResize);
  },
  mounted() {
    this.screenWidth = window.innerWidth;
    this.handleScreenResize();
  },
};
</script>

<style scoped>
img:hover {
  cursor: pointer;
}
</style>
