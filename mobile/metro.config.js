module.exports = {
  resolver: {
    resolveRequest: (context, moduleName, platform) => {
      if (moduleName.startsWith('@/')) {
        moduleName = context.projectRoot + moduleName.replace('@/', '/src/')
      }
      return context.resolveRequest(context, moduleName, platform)
    },
  },
  transformer: {
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: true,
      },
    }),
  },
}
