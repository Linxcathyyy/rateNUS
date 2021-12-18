<template>
  <div>
    <v-text-field
      solo
      v-model="keyword"
      name="search-bar"
      :placeholder="searchHint"
      color="grey darken-3"
      :append-icon="searchIcon"
      v-on:keypress.enter="handleSearch"
      @click:append="handleSearch()"
      autocomplete="off"
    >
    </v-text-field>
  </div>
</template>

<script>
export default {
  name: "SearchBar",
  props: {
    searchHint: String,
  },
  data() {
    return {
      keyword: "",
      searchIcon: "mdi-magnify",
    };
  },
  methods: {
    handleSearch() {
      if (this.keyword) this.$emit("handle-search", this.keyword);
    },
  },
};
</script>