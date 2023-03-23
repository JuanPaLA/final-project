module.exports = {
  preset: 'react-native',
  setupFilesAfterEnv: ['jest-extended/all'],
  testRegex: '(/test/(.*)\\.(test|spec))\\.(ts|js|tsx|jsx)$',
  moduleFileExtensions: ['ts', 'tsx', 'js', 'jsx', 'json', 'node'],
  moduleNameMapper: {
    '^@/(.*)$': '<rootDir>/src/$1',
  },
}
