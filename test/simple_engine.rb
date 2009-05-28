require 'openwfe/engine'
require 'logger'

application_context = {}
application_context[:engine_name] = "simple engine"
application_context[:logger] = Logger.new STDOUT
application_context[:logger].level = Logger::ERROR

engine = OpenWFE::Engine.new(application_context)