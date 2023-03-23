import React from 'react'
import { AppRegistry } from 'react-native'
import { MobileApp } from '@/ui/MobileApp'
import { Core } from '@/core/infrastructure/Core'
import { AxiosHttpClient } from '@/core/infrastructure/http/axios/AxiosHttpClient'

const core = new Core( {httpClient: new AxiosHttpClient('http://127.0.0.1:8080')})

const application = new MobileApp({ core })

const rootElement = () => <>{application.render()}</>

AppRegistry.registerComponent('social_network', () => rootElement)
