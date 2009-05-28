require 'openwfe/contextual'
module OpenWFE
  module Contextual
    #we don't need it, and when we package as jar, we couldn't do it
    def copy_pooltool (dir)
      # target = dir + '/pooltool.ru'
      # return if File.exist?(target)
      # source = File.dirname(__FILE__) + '/../pooltool.ru'
      # FileUtils.cp(source, target)
    end
  end
end
