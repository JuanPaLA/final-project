import { NavigationProp } from '@react-navigation/core/lib/typescript/src/types'
import { RouteProp } from '@react-navigation/native'

export type DefaultNavigationProp = NavigationProp<{ [key: string]: any }>

export type DefaultRouteProp = RouteProp<{ [key: string]: any }>

export type NavigationProps = { navigation: DefaultNavigationProp, route: DefaultRouteProp }
