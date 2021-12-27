import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';
import '@mdi/font/css/materialdesignicons.css'
import colors from 'vuetify/lib/util/colors'

Vue.use(Vuetify);

export default new Vuetify({
  icons: {
    iconfont: 'mdi',
  },
  theme: {
    themes: {
      light: {
        primary: colors.orange.accent4,
        secondary: colors.orange.accent4,
        accent: colors.orange.accent4,
        anchor: colors.orange.accent4,
      },
      dark: {
        primary: colors.orange.accent4,
        secondary: colors.orange.accent4,
        accent: colors.orange.accent4,
        anchor: colors.orange.accent4,
      }
    }
  }
});
