import { createI18n } from 'vue-i18n'
import zh from './zh'
import en from './en'
import ru from './ru'

const savedLang = localStorage.getItem('app_lang') || 'zh'

const i18n = createI18n({
  legacy: false,
  locale: savedLang,
  fallbackLocale: 'zh',
  messages: { zh, en, ru }
})

export default i18n
