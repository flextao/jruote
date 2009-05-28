
require 'rake'

namespace :ruote do

  RUFUSES = %w{ 
    dollar lru mnemo scheduler verbs sixjo treechecker
  }.collect { |e| "rufus-#{e}" }

  desc "get ruote and its rufus dependencies from github default branches"
  task :get_from_github do
    rm_r 'vendor/ruote' if File.exists?('vendor/ruote')
    mkdir 'vendor/ruote' #unless File.exists?('vendor/ruote')

    (RUFUSES + ['ruote']).each { |e| git_clone(e) }

    require File.dirname(__FILE__) + '/tasks/frigo'

    File.open('vendor/ruote/README.txt', 'w') do |f|
      f.write %{
  = vendor

  This directory contains ruote and its rufus dependencies, directly checked
  out of http://github.com

  Each subdir contains the .git/ repository, in case you might want to 'git pull'
  a new version.

      }
    end
  end

  def git_clone (elt)
    chdir 'vendor/ruote' do
      sh "git clone git://github.com/jmettraux/#{elt}.git"
      sh "rm -rf #{elt}/.git"
    end
  end
end