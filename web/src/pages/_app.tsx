import '@/ui/layout/styles/globals.css'

import type { AppProps } from 'next/app'
import { defaultWebAppConfig, WebApp } from '@/ui/WebApp'

const application = new WebApp(defaultWebAppConfig())

const Application = ({ Component, pageProps }: AppProps) => application.render(Component, pageProps)

export default Application
