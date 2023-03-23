import React from 'react'
import { AppRegistry } from 'react-native'
import { MobileApp } from '@/ui/MobileApp'
import { Core } from '@/core/infrastructure/Core'
import { AxiosHttpClient } from '@/core/infrastructure/http/axios/AxiosHttpClient'

const core = new Core( {httpClient: new AxiosHttpClient('http://10.0.2.2:8081')})

const application = new MobileApp({ core })

const rootElement = () => <>{application.render()}</>

AppRegistry.registerComponent('social_network', () => rootElement)
