<template>
  <div class="hostel-description">
    {{ description }}
  </div>
</template>

<script>
export default {
  props: ["description"],
};
</script>

<style>
.hostel-description {
  padding: 10px 30px;
  text-align: left;
  line-height: 120%;
  max-height: 120px;
  overflow-y: auto;
}

.hostel-description::-webkit-scrollbar {
  width: 12px;
}

.hostel-description::-webkit-scrollbar-track {
  background-color: lightgray;
  border-radius: 10px;
}

.hostel-description::-webkit-scrollbar-thumb {
  background-color: rgba(201, 218, 241);
  border-radius: 10px;
}

.hostel-description::-webkit-scrollbar-corner {
  border-radius: 10px;
}
</style>
