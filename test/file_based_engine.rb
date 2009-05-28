require 'openwfe/engine'
require 'logger'

application_context = {}
application_context[:engine_name] = "file based engine"
application_context[:logger] = Logger.new STDOUT
application_context[:logger].level = Logger::ERROR


require 'openwfe/expool/history'
require 'openwfe/engine/fs_engine'

engine = OpenWFE::FsPersistedEngine.new(application_context)
engine.init_service("history", OpenWFE::FileHistory)
engine