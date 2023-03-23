import { Dimensions } from 'react-native'

export { widthPercentageToDP, heightPercentageToDP } from 'react-native-responsive-screen'

export function relativeValueToDP(value: number, referenceWidth: number = 1440) {
    if (value === 0) return 0
    const screenWidth = Dimensions.get('window').width
    const rv = screenWidth * value / referenceWidth
    return Math.max(rv, 1)
}

export const rv = relativeValueToDP
